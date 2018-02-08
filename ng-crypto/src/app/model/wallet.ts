/**
 * Created by AELION on 31/01/2018.
 */



//import {coins, priceToDollar} from "./coins";
import {PricingService} from "../pricing.service";


export class Line {
  constructor(public symbol: string, public quantity: number) {

  }
}

export class Wallet {
  lines: Line[] = [];

  pricingService: PricingService;

  deposit(dollars: number) {
    let usdLline = this.lines.find((coin => coin.symbol === 'USD'));
    if (usdLline === undefined) {
      this.lines.push(new Line('USD', dollars));
    }
    else {
      usdLline.quantity = usdLline.quantity + dollars;
    }
  }

  buy(quantity: number, symbol: string) {
    let usdLline = this.lines.find(line => line.symbol === 'USD')
    let symbolLine = this.lines.find(line => line.symbol === symbol);
    let dollarAmount = usdLline.quantity;
    let coinAmount = this.pricingService.priceToDollar(quantity, symbol);

    usdLline.quantity = dollarAmount - coinAmount;

    if (symbolLine === undefined){
      this.lines.push(new Line(symbol, quantity));
    }
    else {
      symbolLine.quantity =  symbolLine.quantity + quantity;
    }

  }


  sell(quantity: number, symbol: string) {
    let usdLine = this.lines.find(line => line.symbol === 'USD');
    let symbolLine = this.lines.find(line => line.symbol === symbol);
    let symbolAmount = symbolLine.quantity;
    let coinAmount = this.pricingService.priceToDollar(quantity, symbol);

    symbolLine.quantity = symbolAmount - quantity;
    usdLine.quantity = usdLine.quantity + coinAmount;

  }

  totalDollarValue(): number {
    let res = 0;
    for (let i = 0; i < this.lines.length; i++) {
      let line = this.lines[i];
      if (line.symbol === 'USD') {
        res += line.quantity;
      } else {
        res += this.pricingService.priceToDollar(line.quantity, line.symbol);
      }
    }

    return res;
  }


  constructor() {


  }

}

