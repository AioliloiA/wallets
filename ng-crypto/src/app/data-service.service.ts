import { Injectable } from '@angular/core';
import {User} from "./model/user";
import {HttpClient} from "@angular/common/http";
import {Wallet} from "./model/wallet";

@Injectable()
export class DataService {

  constructor(public http:HttpClient) { }
  fetchUsers():Promise<User[]>{

    return this.http
      .get('http://localhost:8080/cryptos/api/users')
      .toPromise()
      .then(data => data as User[])

  }

  fetchUsersWithWallets(user: User): Promise<User>{

    let url = 'http://localhost:8080/cryptos/api/users/'+user.id;
    return this.http
      .get(url)
      .toPromise()
      .then(data => {
        console.log('user with wallet : ', data);
        return data as User
      })
  }

  createWallet(wallet: Wallet){
    let url = 'http://localhost:8080/cryptos/api/wallets';

    let dto = {
      name: wallet.name,
      user: wallet.user
    };

    return this.http.post(url, dto)
      .toPromise()
      .then(data=> console.log('Success', data))

  }

  createUser (user: User){
    let url = 'http://localhost:8080/cryptos/api/users'

    let dto = {
      name: user.name,
      wallets: user.wallets
    }

    return this.http.post(url, dto)
      .toPromise()
      .then(data => console.log('Success',data));

  }

}
