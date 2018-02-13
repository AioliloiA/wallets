package io.pax.cryptos.ws;

import io.pax.cryptos.dao.UserDao;
import io.pax.cryptos.domain.jdbc.FullUser;
import io.pax.cryptos.domain.User;
import io.pax.cryptos.domain.Wallet;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AELION on 09/02/2018.
 */
@Path("users")
@Produces(MediaType.APPLICATION_JSON)
public class UserWs {

    @GET
    public List<User> getUsers() throws SQLException {
        UserDao dao = new UserDao();
        return dao.listUsers();
    }

    @GET
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public User getUser(@PathParam("id") int userId) throws SQLException {

        return new UserDao().findUserWithWallets(userId);
    }

    @POST

    public User createUser(FullUser user) {

        List<Wallet> wallets = new ArrayList<>();

        try {
            int id = new UserDao().createUser(user.getName());


            return new FullUser(id, user.getName(), wallets);


        } catch (SQLException e) {
            throw new ServerErrorException("DTB error", 500);
        }
    }
}