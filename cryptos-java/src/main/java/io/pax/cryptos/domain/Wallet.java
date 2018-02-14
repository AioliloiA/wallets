package io.pax.cryptos.domain;

import io.pax.cryptos.domain.jdbc.SimpleUser;

import java.util.List;

/**
 * Created by AELION on 06/02/2018.
 */
public interface Wallet {

  String getName();

  default SimpleUser getUser(){
    return null;
  }

  int getId();

  List<? extends Line> getLines();

}
