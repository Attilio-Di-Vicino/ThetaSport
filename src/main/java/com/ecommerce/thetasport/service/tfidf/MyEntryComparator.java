package com.ecommerce.thetasport.service.tfidf;

import com.ecommerce.thetasport.service.annotations.Method;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.Map;

/**
 * Questa classe implementa un {@link Comparator} per ordinare una mappa di stringhe e double in ordine decrescente
 * basandosi sul valore associato alla chiave.
 */
public class MyEntryComparator implements Comparator< Map.Entry<String, Double> > {

    /**
     * Compara due {@link Map.Entry} della mappa per determinare l'ordine decrescente basato sul valore associato alla chiave.
     *
     * @param entry1 La prima entry da comparare.
     * @param entry2 La seconda entry da comparare.
     * @return Un intero negativo, zero o un intero positivo se la prima entry è meno di, uguale a, o maggiore della seconda entry.
     */
    @Method( description = "Compara due {@link Map.Entry} della mappa per determinare l'ordine " +
            "decrescente basato sul valore associato alla chiave.",
            parameters = { "entry1 La prima entry da comparare.", "entry2 La seconda entry da comparare." },
            returns = { "Un intero negativo, zero o un intero positivo se la prima " +
                    "entry è meno di, uguale a, o maggiore della seconda entry." }
    )
    @Override
    public int compare( Map.@NotNull Entry<String, Double> entry1, Map.@NotNull Entry<String, Double> entry2 ) {
        // Ordinamento in ordine decrescente in base al valore associato alla chiave
        return Double.compare( entry2.getValue(), entry1.getValue() );
    }
}
