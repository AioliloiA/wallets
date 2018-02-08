import {Injectable} from '@angular/core';
import {Coin} from "./model/coins";
import {HttpClient} from "@angular/common/http";

@Injectable()
export class PricingService {
// Asynchrone coins

  // url: https://api.coinmarketcap.com/v1/ticker/?limit=10
  coins: Coin[];

  constructor(public http: HttpClient) {

  }

  loadPrices() { // ça renvoie une promesse
    let url = 'https://api.coinmarketcap.com/v1/ticker/?limit=10';

    function mapper(coin) {
      return {
        name: coin.name,
        symbol: coin.symbol,
        price: coin.price_usd,
        up: coin.percent_change_1h >= 0
      }

    }


    return this.http.get(url)
      .toPromise()
      .then(internetCoins => (internetCoins as any).map(mapper))
      .then(joliCoins => {
        this.coins = joliCoins;
        return joliCoins;
      });
  }

  priceToDollar(quantity, symbol) {
    for (let i = 0; i < this.coins.length; i++) {
      if (this.coins[i].symbol == symbol) {
        return quantity * this.coins[i].price;
      }

    }



  }

  getColor(symbol):string {
    for (let i = 0; i < this.coins.length; i++) {
      if (this.coins[i].symbol == symbol) {
        if (symbol === 'USD') {return 'black'}
        else if (this.coins[i].up){
          return 'green'
        }
        return 'red'
      }

    }
    return 'black';

  }
}
