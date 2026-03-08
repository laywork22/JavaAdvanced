package org.inter;

/**
 * È possibile permettere sia sottointerfacce che classi
 */
public sealed interface Espressione permits Descrivibile {
    int valuta();
}


