package br.com.fiap.techecomm.products.exception;

public class NoStockProduct extends RuntimeException {
    public NoStockProduct(int totalRequired) {
        super(
                """
                The stock no has %s item this product!
                """.formatted(totalRequired)
        );

    }
}
