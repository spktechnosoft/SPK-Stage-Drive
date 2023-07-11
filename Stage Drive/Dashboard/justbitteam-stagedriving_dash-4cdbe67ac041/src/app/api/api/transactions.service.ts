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
/* tslint:disable:no-unused-variable member-ordering */

import { Inject, Injectable, Optional }                      from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams,
         HttpResponse, HttpEvent }                           from '@angular/common/http';
import { CustomHttpUrlEncodingCodec }                        from '../encoder';

import { Observable }                                        from 'rxjs/Observable';

import { StgdrvResponseDTO } from '../model/stgdrvResponseDTO';
import { TransactionDTO } from '../model/transactionDTO';

import { BASE_PATH, COLLECTION_FORMATS }                     from '../variables';
import { Configuration }                                     from '../configuration';


@Injectable()
export class TransactionsService {

    protected basePath = 'http://localhost';
    public defaultHeaders = new HttpHeaders();
    public configuration = new Configuration();

    constructor(protected httpClient: HttpClient, @Optional()@Inject(BASE_PATH) basePath: string, @Optional() configuration: Configuration) {
        if (basePath) {
            this.basePath = basePath;
        }
        if (configuration) {
            this.configuration = configuration;
            this.basePath = basePath || configuration.basePath || this.basePath;
        }
    }

    /**
     * @param consumes string[] mime-types
     * @return true: consumes contains 'multipart/form-data', false: otherwise
     */
    private canConsumeForm(consumes: string[]): boolean {
        const form = 'multipart/form-data';
        for (let consume of consumes) {
            if (form === consume) {
                return true;
            }
        }
        return false;
    }


    /**
     * New transaction
     * New transaction
     * @param body 
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public addTransaction(body: TransactionDTO, observe?: 'body', reportProgress?: boolean): Observable<StgdrvResponseDTO>;
    public addTransaction(body: TransactionDTO, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<StgdrvResponseDTO>>;
    public addTransaction(body: TransactionDTO, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<StgdrvResponseDTO>>;
    public addTransaction(body: TransactionDTO, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {
        if (body === null || body === undefined) {
            throw new Error('Required parameter body was null or undefined when calling addTransaction.');
        }

        let headers = this.defaultHeaders;

        // to determine the Accept header
        let httpHeaderAccepts: string[] = [
            'application/json'
        ];
        let httpHeaderAcceptSelected: string | undefined = this.configuration.selectHeaderAccept(httpHeaderAccepts);
        if (httpHeaderAcceptSelected != undefined) {
            headers = headers.set("Accept", httpHeaderAcceptSelected);
        }

        // to determine the Content-Type header
        let consumes: string[] = [
            'application/json'
        ];
        let httpContentTypeSelected:string | undefined = this.configuration.selectHeaderContentType(consumes);
        if (httpContentTypeSelected != undefined) {
            headers = headers.set("Content-Type", httpContentTypeSelected);
        }

        return this.httpClient.post<StgdrvResponseDTO>(`${this.basePath}/v1/transactions`,
            body,
            {
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

    /**
     * Delete transaction
     * Delete transaction
     * @param transactionId 
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public deleteTransaction(transactionId: string, observe?: 'body', reportProgress?: boolean): Observable<StgdrvResponseDTO>;
    public deleteTransaction(transactionId: string, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<StgdrvResponseDTO>>;
    public deleteTransaction(transactionId: string, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<StgdrvResponseDTO>>;
    public deleteTransaction(transactionId: string, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {
        if (transactionId === null || transactionId === undefined) {
            throw new Error('Required parameter transactionId was null or undefined when calling deleteTransaction.');
        }

        let headers = this.defaultHeaders;

        // to determine the Accept header
        let httpHeaderAccepts: string[] = [
            'application/json'
        ];
        let httpHeaderAcceptSelected: string | undefined = this.configuration.selectHeaderAccept(httpHeaderAccepts);
        if (httpHeaderAcceptSelected != undefined) {
            headers = headers.set("Accept", httpHeaderAcceptSelected);
        }

        // to determine the Content-Type header
        let consumes: string[] = [
        ];

        return this.httpClient.delete<StgdrvResponseDTO>(`${this.basePath}/v1/transactions/${encodeURIComponent(String(transactionId))}`,
            {
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

    /**
     * Edit transaction
     * Edit transaction
     * @param body 
     * @param transactionId 
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public editTransaction(body: TransactionDTO, transactionId: string, observe?: 'body', reportProgress?: boolean): Observable<StgdrvResponseDTO>;
    public editTransaction(body: TransactionDTO, transactionId: string, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<StgdrvResponseDTO>>;
    public editTransaction(body: TransactionDTO, transactionId: string, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<StgdrvResponseDTO>>;
    public editTransaction(body: TransactionDTO, transactionId: string, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {
        if (body === null || body === undefined) {
            throw new Error('Required parameter body was null or undefined when calling editTransaction.');
        }
        if (transactionId === null || transactionId === undefined) {
            throw new Error('Required parameter transactionId was null or undefined when calling editTransaction.');
        }

        let headers = this.defaultHeaders;

        // to determine the Accept header
        let httpHeaderAccepts: string[] = [
            'application/json'
        ];
        let httpHeaderAcceptSelected: string | undefined = this.configuration.selectHeaderAccept(httpHeaderAccepts);
        if (httpHeaderAcceptSelected != undefined) {
            headers = headers.set("Accept", httpHeaderAcceptSelected);
        }

        // to determine the Content-Type header
        let consumes: string[] = [
        ];
        let httpContentTypeSelected:string | undefined = this.configuration.selectHeaderContentType(consumes);
        if (httpContentTypeSelected != undefined) {
            headers = headers.set("Content-Type", httpContentTypeSelected);
        }

        return this.httpClient.put<StgdrvResponseDTO>(`${this.basePath}/v1/transactions/${encodeURIComponent(String(transactionId))}`,
            body,
            {
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

    /**
     * Retrieve transaction
     * Retrieve transaction
     * @param transactionId 
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public getTransaction(transactionId: string, observe?: 'body', reportProgress?: boolean): Observable<TransactionDTO>;
    public getTransaction(transactionId: string, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<TransactionDTO>>;
    public getTransaction(transactionId: string, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<TransactionDTO>>;
    public getTransaction(transactionId: string, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {
        if (transactionId === null || transactionId === undefined) {
            throw new Error('Required parameter transactionId was null or undefined when calling getTransaction.');
        }

        let headers = this.defaultHeaders;

        // to determine the Accept header
        let httpHeaderAccepts: string[] = [
            'application/json'
        ];
        let httpHeaderAcceptSelected: string | undefined = this.configuration.selectHeaderAccept(httpHeaderAccepts);
        if (httpHeaderAcceptSelected != undefined) {
            headers = headers.set("Accept", httpHeaderAcceptSelected);
        }

        // to determine the Content-Type header
        let consumes: string[] = [
        ];

        return this.httpClient.get<TransactionDTO>(`${this.basePath}/v1/transactions/${encodeURIComponent(String(transactionId))}`,
            {
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

    /**
     * Retrieves transactions
     * Retrieves transactions
     * @param fromAccountId From Account Id
     * @param toAccountId To Account Id
     * @param eventId Event Id
     * @param rideId Ride Id
     * @param order Order
     * @param sort Sort
     * @param idLike Id like
     * @param amountLike Amount like
     * @param feeLike Fee like
     * @param totalAmountLike TotalAmount like
     * @param statusLike Status like
     * @param providerLike Provider like
     * @param size Size
     * @param limit Response page size
     * @param page Response page index
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public getTransactions(fromAccountId?: string, toAccountId?: string, eventId?: string, rideId?: string, order?: string, sort?: string, idLike?: string, amountLike?: string, feeLike?: string, totalAmountLike?: string, statusLike?: string, providerLike?: string, size?: boolean, limit?: string, page?: string, observe?: 'body', reportProgress?: boolean): Observable<Array<TransactionDTO>>;
    public getTransactions(fromAccountId?: string, toAccountId?: string, eventId?: string, rideId?: string, order?: string, sort?: string, idLike?: string, amountLike?: string, feeLike?: string, totalAmountLike?: string, statusLike?: string, providerLike?: string, size?: boolean, limit?: string, page?: string, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<Array<TransactionDTO>>>;
    public getTransactions(fromAccountId?: string, toAccountId?: string, eventId?: string, rideId?: string, order?: string, sort?: string, idLike?: string, amountLike?: string, feeLike?: string, totalAmountLike?: string, statusLike?: string, providerLike?: string, size?: boolean, limit?: string, page?: string, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<Array<TransactionDTO>>>;
    public getTransactions(fromAccountId?: string, toAccountId?: string, eventId?: string, rideId?: string, order?: string, sort?: string, idLike?: string, amountLike?: string, feeLike?: string, totalAmountLike?: string, statusLike?: string, providerLike?: string, size?: boolean, limit?: string, page?: string, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        let queryParameters = new HttpParams({encoder: new CustomHttpUrlEncodingCodec()});
        if (fromAccountId !== undefined) {
            queryParameters = queryParameters.set('fromAccountId', <any>fromAccountId);
        }
        if (toAccountId !== undefined) {
            queryParameters = queryParameters.set('toAccountId', <any>toAccountId);
        }
        if (eventId !== undefined) {
            queryParameters = queryParameters.set('eventId', <any>eventId);
        }
        if (rideId !== undefined) {
            queryParameters = queryParameters.set('rideId', <any>rideId);
        }
        if (order !== undefined) {
            queryParameters = queryParameters.set('order', <any>order);
        }
        if (sort !== undefined) {
            queryParameters = queryParameters.set('sort', <any>sort);
        }
        if (idLike !== undefined) {
            queryParameters = queryParameters.set('id_like', <any>idLike);
        }
        if (amountLike !== undefined) {
            queryParameters = queryParameters.set('amount_like', <any>amountLike);
        }
        if (feeLike !== undefined) {
            queryParameters = queryParameters.set('fee_like', <any>feeLike);
        }
        if (totalAmountLike !== undefined) {
            queryParameters = queryParameters.set('totalAmount_like', <any>totalAmountLike);
        }
        if (statusLike !== undefined) {
            queryParameters = queryParameters.set('status_like', <any>statusLike);
        }
        if (providerLike !== undefined) {
            queryParameters = queryParameters.set('provider_like', <any>providerLike);
        }
        if (size !== undefined) {
            queryParameters = queryParameters.set('size', <any>size);
        }
        if (limit !== undefined) {
            queryParameters = queryParameters.set('limit', <any>limit);
        }
        if (page !== undefined) {
            queryParameters = queryParameters.set('page', <any>page);
        }

        let headers = this.defaultHeaders;

        // to determine the Accept header
        let httpHeaderAccepts: string[] = [
            'application/json'
        ];
        let httpHeaderAcceptSelected: string | undefined = this.configuration.selectHeaderAccept(httpHeaderAccepts);
        if (httpHeaderAcceptSelected != undefined) {
            headers = headers.set("Accept", httpHeaderAcceptSelected);
        }

        // to determine the Content-Type header
        let consumes: string[] = [
        ];

        return this.httpClient.get<Array<TransactionDTO>>(`${this.basePath}/v1/transactions`,
            {
                params: queryParameters,
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

}