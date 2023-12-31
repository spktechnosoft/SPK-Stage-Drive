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

import { CheckinDTO } from '../model/checkinDTO';
import { StgdrvResponseDTO } from '../model/stgdrvResponseDTO';

import { BASE_PATH, COLLECTION_FORMATS }                     from '../variables';
import { Configuration }                                     from '../configuration';


@Injectable()
export class CheckinsService {

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
     * Add new checkin
     * Add new checkin
     * @param body 
     * @param authorization Authorization
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public createCheckin(body: CheckinDTO, authorization: string, observe?: 'body', reportProgress?: boolean): Observable<StgdrvResponseDTO>;
    public createCheckin(body: CheckinDTO, authorization: string, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<StgdrvResponseDTO>>;
    public createCheckin(body: CheckinDTO, authorization: string, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<StgdrvResponseDTO>>;
    public createCheckin(body: CheckinDTO, authorization: string, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {
        if (body === null || body === undefined) {
            throw new Error('Required parameter body was null or undefined when calling createCheckin.');
        }
        if (authorization === null || authorization === undefined) {
            throw new Error('Required parameter authorization was null or undefined when calling createCheckin.');
        }

        let headers = this.defaultHeaders;
        if (authorization !== undefined && authorization !== null) {
            headers = headers.set('Authorization', String(authorization));
        }

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

        return this.httpClient.post<StgdrvResponseDTO>(`${this.basePath}/v1/checkins`,
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
     * Delete existing checkin
     * Delete existing checkin
     * @param checkinId 
     * @param authorization Authorization
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public deleteCheckin(checkinId: string, authorization: string, observe?: 'body', reportProgress?: boolean): Observable<StgdrvResponseDTO>;
    public deleteCheckin(checkinId: string, authorization: string, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<StgdrvResponseDTO>>;
    public deleteCheckin(checkinId: string, authorization: string, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<StgdrvResponseDTO>>;
    public deleteCheckin(checkinId: string, authorization: string, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {
        if (checkinId === null || checkinId === undefined) {
            throw new Error('Required parameter checkinId was null or undefined when calling deleteCheckin.');
        }
        if (authorization === null || authorization === undefined) {
            throw new Error('Required parameter authorization was null or undefined when calling deleteCheckin.');
        }

        let headers = this.defaultHeaders;
        if (authorization !== undefined && authorization !== null) {
            headers = headers.set('Authorization', String(authorization));
        }

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

        return this.httpClient.delete<StgdrvResponseDTO>(`${this.basePath}/v1/checkins/${encodeURIComponent(String(checkinId))}`,
            {
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

    /**
     * Retrieve checkin
     * Retrieve checkin
     * @param checkinId 
     * @param authorization Authorization
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public getApp(checkinId: string, authorization: string, observe?: 'body', reportProgress?: boolean): Observable<CheckinDTO>;
    public getApp(checkinId: string, authorization: string, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<CheckinDTO>>;
    public getApp(checkinId: string, authorization: string, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<CheckinDTO>>;
    public getApp(checkinId: string, authorization: string, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {
        if (checkinId === null || checkinId === undefined) {
            throw new Error('Required parameter checkinId was null or undefined when calling getApp.');
        }
        if (authorization === null || authorization === undefined) {
            throw new Error('Required parameter authorization was null or undefined when calling getApp.');
        }

        let headers = this.defaultHeaders;
        if (authorization !== undefined && authorization !== null) {
            headers = headers.set('Authorization', String(authorization));
        }

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

        return this.httpClient.get<CheckinDTO>(`${this.basePath}/v1/checkins/${encodeURIComponent(String(checkinId))}`,
            {
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

    /**
     * Retrieve checkin
     * Retrieves checkin
     * @param authorization Authorization
     * @param limit 
     * @param page 
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public getCheckins(authorization: string, limit?: string, page?: string, observe?: 'body', reportProgress?: boolean): Observable<Array<CheckinDTO>>;
    public getCheckins(authorization: string, limit?: string, page?: string, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<Array<CheckinDTO>>>;
    public getCheckins(authorization: string, limit?: string, page?: string, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<Array<CheckinDTO>>>;
    public getCheckins(authorization: string, limit?: string, page?: string, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {
        if (authorization === null || authorization === undefined) {
            throw new Error('Required parameter authorization was null or undefined when calling getCheckins.');
        }

        let queryParameters = new HttpParams({encoder: new CustomHttpUrlEncodingCodec()});
        if (limit !== undefined) {
            queryParameters = queryParameters.set('limit', <any>limit);
        }
        if (page !== undefined) {
            queryParameters = queryParameters.set('page', <any>page);
        }

        let headers = this.defaultHeaders;
        if (authorization !== undefined && authorization !== null) {
            headers = headers.set('Authorization', String(authorization));
        }

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

        return this.httpClient.get<Array<CheckinDTO>>(`${this.basePath}/v1/checkins`,
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
     * Modify existing checkin
     * Modify existing checkin
     * @param checkinId 
     * @param authorization Authorization
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public modifyCheckin(checkinId: string, authorization: string, observe?: 'body', reportProgress?: boolean): Observable<StgdrvResponseDTO>;
    public modifyCheckin(checkinId: string, authorization: string, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<StgdrvResponseDTO>>;
    public modifyCheckin(checkinId: string, authorization: string, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<StgdrvResponseDTO>>;
    public modifyCheckin(checkinId: string, authorization: string, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {
        if (checkinId === null || checkinId === undefined) {
            throw new Error('Required parameter checkinId was null or undefined when calling modifyCheckin.');
        }
        if (authorization === null || authorization === undefined) {
            throw new Error('Required parameter authorization was null or undefined when calling modifyCheckin.');
        }

        let headers = this.defaultHeaders;
        if (authorization !== undefined && authorization !== null) {
            headers = headers.set('Authorization', String(authorization));
        }

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

        return this.httpClient.put<StgdrvResponseDTO>(`${this.basePath}/v1/checkins/${encodeURIComponent(String(checkinId))}`,
            null,
            {
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

}
