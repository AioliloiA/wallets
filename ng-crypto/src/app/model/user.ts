import {Wallet} from "./wallet";
/**
 * Created by AELION on 09/02/2018.
 */
export class User{

  id: number;
  name: string;

  wallets: Wallet[] = []
}
