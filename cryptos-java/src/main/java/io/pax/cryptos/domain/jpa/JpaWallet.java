package io.pax.cryptos.domain.jpa;

import io.pax.cryptos.domain.Wallet;
import io.pax.cryptos.domain.jdbc.SimpleUser;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AELION on 13/02/2018.
 */
@Entity
public class JpaWallet implements Wallet{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    String name;

    @Transient
    List<JpaLine> lines = new ArrayList<>();

    @Override
    public int getId() {
        return id;
    }

    @Override
    public List<JpaLine> getLines() {
        return lines;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public SimpleUser getUser() {
        return null;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLines(List<JpaLine> lines) {
        this.lines = lines;
    }

}
