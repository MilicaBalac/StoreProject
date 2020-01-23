import { Ad } from './Ad';

export interface Auction {
    
    id?:number;
    text: string;
    user: string;
    amount: number;
    adDTO?: Ad;

}