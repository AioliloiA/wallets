import {Component, OnInit} from '@angular/core';
import {Wallet} from "../model/wallet";
import {PricingService} from "../pricing.service";

@Component({
  selector: 'app-wallet-view',
  templateUrl: 'wallet-view.component.html',
  styleUrls: ['wallet-view.component.css']
})
export class WalletViewComponent implements OnInit {

  money = 0;
  wallet = new Wallet;

  constructor(public pricingService: PricingService) {
    this.wallet.pricingService = pricingService;
    pricingService.loadPrices()
      .then(data => console.log(data))
      .then(() => this.initWallet());
    //     ^^^ function without param ; prices are now loaded

  }

  ngOnInit() {
  }

  deposit(value: string) {
    this.wallet.deposit(parseInt(value));
  }

  buy(symbol: string, quantity: string) {
    this.wallet.buy(parseInt(quantity), symbol);

  }

  sell(quantity: string, symbol: string) {
    this.wallet.sell(parseInt(quantity), symbol);

  }

  getColor(symbol) {
    return this.pricingService.getColor(symbol);
  }

  initWallet() {
    this.wallet.deposit(0);
    this.wallet.buy(0, 'BTC');
    this.wallet.buy(0, 'XRP');
    this.wallet.buy(0, 'ETH');
    this.wallet.buy(0, 'BCH');
    this.wallet.buy(0, 'ADA');
    this.wallet.buy(0, 'LTC');
    this.wallet.buy(0, 'XLM');
    this.wallet.buy(0, 'NEO');
    this.wallet.buy(0, 'EOS');
    this.wallet.buy(0, 'XEM');


  }

  update() {
    return this.pricingService.loadPrices();
  }
}
