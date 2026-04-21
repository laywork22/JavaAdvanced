package it.unisa;

public class IdentityKeeper {
    private final Identity identity;

    public IdentityKeeper(Identity identity) {
        this.identity = identity;
    }

    public enum Identity {
        SERVER,
        CLIENT
    }

    public Identity getIdentity() {
        return identity;
    }
}
