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
import { EventDTO } from './eventDTO';


export interface EventInterestDTO {
    id?: string;
    created?: string;
    modified?: string;
    event?: EventDTO;
    account?: AccountDTO;
    bookingId?: string;
    bookingTicketId?: string;
    actionLikeId?: string;
    actionCommentId?: string;
    actionRideId?: string;
}
