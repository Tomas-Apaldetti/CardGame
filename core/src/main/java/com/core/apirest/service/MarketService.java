package com.core.apirest.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.core.apirest.model.UserAPI;
import com.core.g3.Card.CardName;
import com.core.g3.Commons.Amount;
import com.core.g3.Deck.ICard;
import com.core.g3.Market.Market;
import com.core.g3.Market.Exceptions.NotEnoughFoundsException;
import com.core.g3.Market.Exceptions.PublisherIsBuyerException;
import com.core.g3.Market.Transactions.ITransaction;
import com.core.g3.Market.Transactions.ITransactionable;
import com.core.g3.Market.Transactions.Status.TransactionStatus;

@Component
public class MarketService {
    Market mk = new Market();

    @Autowired
    private UserService userService;

    // venta de una carta
    public ResponseEntity<String> sellCard(UserAPI user, String cardName, int price) {

        ICard card = userService.getCardByName(user, CardName.valueOf(cardName));
        if (card == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro la carta");
        }
        
        try {
            mk.publishTransaction(user.user, (ITransactionable) card , new Amount(price));   
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("No se pudo publicar la carta");
        }

        return ResponseEntity.ok("Carta publicada");
    }

    // ver publicaciones del market
    public List<String> getPublications() {
        Collection<ITransaction> publications = mk.retrievePublications();
        // return a tuple of [cardName, price]
        return publications.stream().map(p -> p.getName().toString() + " " + p.getPrice().toString())
                .collect(Collectors.toList());
    }

    // comprar una carta del market
    public ResponseEntity<String> buyCard(UserAPI user, String cardName, int price) {
        Collection<ITransaction> publications = mk.retrievePublications();
        ITransaction publication = publications.stream()
                .filter(p -> p.getName().toString().equals(cardName) && p.getPrice() == price).findFirst()
                .orElse(null);
        if (publication == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro publicacion para la carta: " + cardName + " con precio: " + price);
        }
        TransactionStatus status;
        try {
            status = mk.doTransaction(publication, user.user);   
        } catch (NotEnoughFoundsException e) {
            return ResponseEntity.badRequest().body("No tienes dinero suficiente para comprar la carta");
        } catch (PublisherIsBuyerException e) {
            return ResponseEntity.badRequest().body("No puedes comprar tu propia carta publicada");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("No se pudo comprar la carta");
        }
        if (status != TransactionStatus.TRANSACTION_APPLIED) {
            return ResponseEntity.badRequest().body("No se pudo comprar la carta");
        }
        return ResponseEntity.ok("Carta comprada");
    }

}
