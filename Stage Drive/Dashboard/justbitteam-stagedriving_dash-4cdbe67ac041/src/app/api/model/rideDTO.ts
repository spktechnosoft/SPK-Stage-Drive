/**
 * Stage Driving API
 * API specifications
 *
 * OpenAPI spec version: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */
import { AccountDTO } from './accountDTO';
import { ActionDTO } from './actionDTO';
import { CoordinateDTO } from './coordinateDTO';
import { EventDTO } from './eventDTO';
import { RidePassengerDTO } from './ridePassengerDTO';
import { RidePriceDTO } from './ridePriceDTO';


/**
 * Ride model
 */
export interface RideDTO {
    id?: string;
    created?: string;
    modified?: string;
    fromCoordinate: CoordinateDTO;
    toCoordinate: CoordinateDTO;
    seats: number;
    goingDepartureDate: string;
    goingArrivalDate: string;
    price: RidePriceDTO;
    totalPrice?: number;
    currency?: string;
    returnDepartureDate?: string;
    returnArrivalDate?: string;
    eventId?: string;
    status?: string;
    visible?: boolean;
    accountId?: string;
    fromEventId?: string;
    toEventId?: string;
    fromEvent?: EventDTO;
    toEvent?: EventDTO;
    withTickets?: boolean;
    withBookings?: boolean;
    withFriends?: boolean;
    hasReturn?: boolean;
    availableSeats?: number;
    bookedSeats?: number;
    userPassenger?: RidePassengerDTO;
    event?: EventDTO;
    account?: AccountDTO;
    actions?: Array<ActionDTO>;
    passengers?: Array<RidePassengerDTO>;
    friends?: Array<RidePassengerDTO>;
}
