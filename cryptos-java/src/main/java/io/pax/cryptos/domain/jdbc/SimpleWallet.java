package io.pax.cryptos.domain.jdbc;

import io.pax.cryptos.domain.Wallet;

/**
 * Created by AELION on 06/02/2018.
 */
public class SimpleWallet implements Wallet {

  int id;
  String name;

  public SimpleWallet(){

  }
  public SimpleWallet(int id, String name) {
    this.id = id;
    this.name = name;
  }

  @Override
  public int getId() {
    return this.id;
  }

  @Override
  public SimpleUser getUser(){
    return null;
  }

  @Override
  public String getName() {
    return this.name;
  }


  @Override
  public String toString() {
    return this.name+"("+this.id+")";
  }
}
