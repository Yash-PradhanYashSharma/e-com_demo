import { CartAdjustment } from "./CartAdjustment";

export class TotalAdjustment {

    userId: string = null;
    freightAdjustment: CartAdjustment = new CartAdjustment(ADJUSTMENT_TYPES.FREIGHT);
    taxAdjustment: CartAdjustment = new CartAdjustment(ADJUSTMENT_TYPES.TAXS);
    promoAdjustment: CartAdjustment = new CartAdjustment(ADJUSTMENT_TYPES.PROMO);

    constructor() {
        console.log('im being initiallized');
    }

}

export const ADJUSTMENT_TYPES =
{
    FREIGHT: 'FREIGHT',
    TAXS: 'TAXS',
    PROMO: 'PROMO'
}