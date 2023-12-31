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

import { ReviewDTO } from '../model/reviewDTO';
import { StgdrvResponseDTO } from '../model/stgdrvResponseDTO';

import { BASE_PATH, COLLECTION_FORMATS }                     from '../variables';
import { Configuration }                                     from '../configuration';


@Injectable()
export class ReviewsService {

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
     * New review
     * New review
     * @param body 
     * @param accountId 
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public createReview(body: ReviewDTO, accountId: string, observe?: 'body', reportProgress?: boolean): Observable<StgdrvResponseDTO>;
    public createReview(body: ReviewDTO, accountId: string, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<StgdrvResponseDTO>>;
    public createReview(body: ReviewDTO, accountId: string, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<StgdrvResponseDTO>>;
    public createReview(body: ReviewDTO, accountId: string, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {
        if (body === null || body === undefined) {
            throw new Error('Required parameter body was null or undefined when calling createReview.');
        }
        if (accountId === null || accountId === undefined) {
            throw new Error('Required parameter accountId was null or undefined when calling createReview.');
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

        return this.httpClient.post<StgdrvResponseDTO>(`${this.basePath}/v1/accounts/${encodeURIComponent(String(accountId))}/reviews`,
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
     * New review
     * New review
     * @param body 
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public createReview1(body: ReviewDTO, observe?: 'body', reportProgress?: boolean): Observable<StgdrvResponseDTO>;
    public createReview1(body: ReviewDTO, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<StgdrvResponseDTO>>;
    public createReview1(body: ReviewDTO, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<StgdrvResponseDTO>>;
    public createReview1(body: ReviewDTO, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {
        if (body === null || body === undefined) {
            throw new Error('Required parameter body was null or undefined when calling createReview1.');
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

        return this.httpClient.post<StgdrvResponseDTO>(`${this.basePath}/v1/reviews`,
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
     * Delete existing review
     * Delete existing review
     * @param reviewId 
     * @param accountId 
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public deleteReview(reviewId: string, accountId: string, observe?: 'body', reportProgress?: boolean): Observable<StgdrvResponseDTO>;
    public deleteReview(reviewId: string, accountId: string, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<StgdrvResponseDTO>>;
    public deleteReview(reviewId: string, accountId: string, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<StgdrvResponseDTO>>;
    public deleteReview(reviewId: string, accountId: string, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {
        if (reviewId === null || reviewId === undefined) {
            throw new Error('Required parameter reviewId was null or undefined when calling deleteReview.');
        }
        if (accountId === null || accountId === undefined) {
            throw new Error('Required parameter accountId was null or undefined when calling deleteReview.');
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

        return this.httpClient.delete<StgdrvResponseDTO>(`${this.basePath}/v1/accounts/${encodeURIComponent(String(accountId))}/reviews/${encodeURIComponent(String(reviewId))}`,
            {
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

    /**
     * Delete existing review
     * Delete existing review
     * @param reviewId 
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public deleteReview1(reviewId: string, observe?: 'body', reportProgress?: boolean): Observable<StgdrvResponseDTO>;
    public deleteReview1(reviewId: string, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<StgdrvResponseDTO>>;
    public deleteReview1(reviewId: string, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<StgdrvResponseDTO>>;
    public deleteReview1(reviewId: string, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {
        if (reviewId === null || reviewId === undefined) {
            throw new Error('Required parameter reviewId was null or undefined when calling deleteReview1.');
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

        return this.httpClient.delete<StgdrvResponseDTO>(`${this.basePath}/v1/reviews/${encodeURIComponent(String(reviewId))}`,
            {
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

    /**
     * Retrieve review
     * Retrieve review
     * @param reviewId 
     * @param accountId 
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public getReview(reviewId: string, accountId: string, observe?: 'body', reportProgress?: boolean): Observable<ReviewDTO>;
    public getReview(reviewId: string, accountId: string, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<ReviewDTO>>;
    public getReview(reviewId: string, accountId: string, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<ReviewDTO>>;
    public getReview(reviewId: string, accountId: string, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {
        if (reviewId === null || reviewId === undefined) {
            throw new Error('Required parameter reviewId was null or undefined when calling getReview.');
        }
        if (accountId === null || accountId === undefined) {
            throw new Error('Required parameter accountId was null or undefined when calling getReview.');
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

        return this.httpClient.get<ReviewDTO>(`${this.basePath}/v1/accounts/${encodeURIComponent(String(accountId))}/reviews/${encodeURIComponent(String(reviewId))}`,
            {
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

    /**
     * Retrieve review
     * Retrieve review
     * @param reviewId 
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public getReview1(reviewId: string, observe?: 'body', reportProgress?: boolean): Observable<ReviewDTO>;
    public getReview1(reviewId: string, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<ReviewDTO>>;
    public getReview1(reviewId: string, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<ReviewDTO>>;
    public getReview1(reviewId: string, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {
        if (reviewId === null || reviewId === undefined) {
            throw new Error('Required parameter reviewId was null or undefined when calling getReview1.');
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

        return this.httpClient.get<ReviewDTO>(`${this.basePath}/v1/reviews/${encodeURIComponent(String(reviewId))}`,
            {
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

    /**
     * Retrieves reviews
     * Retrieves reviews
     * @param accountId2 
     * @param limit Response page size
     * @param page Response page index
     * @param accountId 
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public getReviews(accountId2: string, limit?: string, page?: string, accountId?: string, observe?: 'body', reportProgress?: boolean): Observable<Array<ReviewDTO>>;
    public getReviews(accountId2: string, limit?: string, page?: string, accountId?: string, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<Array<ReviewDTO>>>;
    public getReviews(accountId2: string, limit?: string, page?: string, accountId?: string, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<Array<ReviewDTO>>>;
    public getReviews(accountId2: string, limit?: string, page?: string, accountId?: string, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {
        if (accountId2 === null || accountId2 === undefined) {
            throw new Error('Required parameter accountId2 was null or undefined when calling getReviews.');
        }

        let queryParameters = new HttpParams({encoder: new CustomHttpUrlEncodingCodec()});
        if (limit !== undefined) {
            queryParameters = queryParameters.set('limit', <any>limit);
        }
        if (page !== undefined) {
            queryParameters = queryParameters.set('page', <any>page);
        }
        if (accountId !== undefined) {
            queryParameters = queryParameters.set('accountId', <any>accountId);
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

        return this.httpClient.get<Array<ReviewDTO>>(`${this.basePath}/v1/accounts/${encodeURIComponent(String(accountId))}/reviews`,
            {
                params: queryParameters,
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

    /**
     * Retrieves reviews
     * Retrieves reviews
     * @param limit Response page size
     * @param page Response page index
     * @param accountId 
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public getReviews1(limit?: string, page?: string, accountId?: string, observe?: 'body', reportProgress?: boolean): Observable<Array<ReviewDTO>>;
    public getReviews1(limit?: string, page?: string, accountId?: string, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<Array<ReviewDTO>>>;
    public getReviews1(limit?: string, page?: string, accountId?: string, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<Array<ReviewDTO>>>;
    public getReviews1(limit?: string, page?: string, accountId?: string, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        let queryParameters = new HttpParams({encoder: new CustomHttpUrlEncodingCodec()});
        if (limit !== undefined) {
            queryParameters = queryParameters.set('limit', <any>limit);
        }
        if (page !== undefined) {
            queryParameters = queryParameters.set('page', <any>page);
        }
        if (accountId !== undefined) {
            queryParameters = queryParameters.set('accountId', <any>accountId);
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

        return this.httpClient.get<Array<ReviewDTO>>(`${this.basePath}/v1/reviews`,
            {
                params: queryParameters,
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

    /**
     * Modify existing review
     * Modify existing review
     * @param body 
     * @param reviewId 
     * @param accountId 
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public modifyReview(body: ReviewDTO, reviewId: string, accountId: string, observe?: 'body', reportProgress?: boolean): Observable<StgdrvResponseDTO>;
    public modifyReview(body: ReviewDTO, reviewId: string, accountId: string, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<StgdrvResponseDTO>>;
    public modifyReview(body: ReviewDTO, reviewId: string, accountId: string, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<StgdrvResponseDTO>>;
    public modifyReview(body: ReviewDTO, reviewId: string, accountId: string, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {
        if (body === null || body === undefined) {
            throw new Error('Required parameter body was null or undefined when calling modifyReview.');
        }
        if (reviewId === null || reviewId === undefined) {
            throw new Error('Required parameter reviewId was null or undefined when calling modifyReview.');
        }
        if (accountId === null || accountId === undefined) {
            throw new Error('Required parameter accountId was null or undefined when calling modifyReview.');
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

        return this.httpClient.put<StgdrvResponseDTO>(`${this.basePath}/v1/accounts/${encodeURIComponent(String(accountId))}/reviews/${encodeURIComponent(String(reviewId))}`,
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
     * Modify existing review
     * Modify existing review
     * @param body 
     * @param reviewId 
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public modifyReview1(body: ReviewDTO, reviewId: string, observe?: 'body', reportProgress?: boolean): Observable<StgdrvResponseDTO>;
    public modifyReview1(body: ReviewDTO, reviewId: string, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<StgdrvResponseDTO>>;
    public modifyReview1(body: ReviewDTO, reviewId: string, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<StgdrvResponseDTO>>;
    public modifyReview1(body: ReviewDTO, reviewId: string, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {
        if (body === null || body === undefined) {
            throw new Error('Required parameter body was null or undefined when calling modifyReview1.');
        }
        if (reviewId === null || reviewId === undefined) {
            throw new Error('Required parameter reviewId was null or undefined when calling modifyReview1.');
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

        return this.httpClient.put<StgdrvResponseDTO>(`${this.basePath}/v1/reviews/${encodeURIComponent(String(reviewId))}`,
            body,
            {
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

}
