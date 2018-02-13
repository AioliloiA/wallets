package io.pax.cryptos.domain.jdbc;

/**
 * Created by AELION on 12/02/2018.
 */
public class FullWallet extends SimpleWallet {

    SimpleUser user;

    public FullWallet(){
        super();
    }
    public FullWallet(int id, String name, SimpleUser user) {
        super(id, name);
        this.user = user;
    }

    public void setUser(SimpleUser user){
        this.user = user;
    }

    @Override
    public SimpleUser getUser(){
        return user;
    }



}
