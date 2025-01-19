package br.com.fiap.tech_challenge.core.domain.model.enums;

public enum SituacaoPedido {
    AGUARDANDO_PAGAMENTO (1),
    PAGAMENTO_RECEBIDO (2),
    EM_PREPARACAO (3),
    PRONTO_PARA_RETIRADA (4),
    FINALIZADO (5);

    private final int ordem;

    SituacaoPedido(int ordem) {
        this.ordem = ordem;
    }

    public int getOrdem() {
        return ordem;
    }

}
