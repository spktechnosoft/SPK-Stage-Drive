/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stagedriving.modules.commons.utils.location;

/**
 * Project stage driving api
 * Author: man
 * Date: 17/10/2018
 */
public class GeoLocationUtils {

    private static double EARTH_RADIUS_MILES = 3959.00;
    private static double EARTH_RADIUS_KM = 6371.009;
    
    /**
     * Method used to convert the value form radians to degrees
     *
     * @param rad
     * @return value in degrees
     */
    private static double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }
    
    /**
     * Converts the value from Degrees to radians
     *
     * @param deg
     * @return value in radians
     */
    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }
    
    /**
     * Returns the difference in degrees of longitude corresponding to the
     * distance from the center point. This distance can be used to find the
     * extreme points.
     *
     * @param p1
     * @param distance
     * @return
     */
    private static double getExtremeLongitudesDiffForPoint(Point p1, double distance, double radius) {
        double lat1 = p1.latitude;
        lat1 = deg2rad(lat1);
        double longitudeRadius = Math.cos(lat1) * radius;
        double diffLong = (distance / longitudeRadius);
        diffLong = rad2deg(diffLong);
        return diffLong;
    }
    
    /**
     * Returns the difference in degrees of latitude corresponding to the
     * distance from the center point. This distance can be used to find the
     * extreme points.
     *
     * @param p1
     * @param distance
     * @return
     */
    private static double getExtremeLatitudesDiffForPoint(Point p1, double distance, double radius) {
        double latitudeRadians = distance / radius;
        double diffLat = rad2deg(latitudeRadians);
        return diffLat;
    }
    
    /**
     * Returns an array of two extreme points corresponding to center point and
     * the distance from the center point. These extreme points are the points
     * with max/min latitude and longitude.
     *
     * @param point
     * @param distance
     * @return
     */
    public static Point[] getExtremePointsFrom(Point point, Double distance, double radius) {
        double longDiff = getExtremeLongitudesDiffForPoint(point, distance, radius);
        double latDiff = getExtremeLatitudesDiffForPoint(point, distance, radius);
        Point p1 = new Point(point.latitude - latDiff, point.longitude - longDiff);
        p1 = validatePoint(p1);
        Point p2 = new Point(point.latitude + latDiff, point.longitude + longDiff);
        p2 = validatePoint(p2);
        
        return new Point[]{p1, p2};
    }
    
    /**
     * Validates if the point passed has valid values in degrees i.e. latitude
     * lies between -90 and +90 and the longitude
     *
     * @param point
     * @return
     */
    private static Point validatePoint(Point point) {
        if (point.latitude > 90) {
            point.latitude = 90 - (point.latitude - 90);
        }
        if (point.latitude < -90) {
            point.latitude = -90 - (point.latitude + 90);
        }
        if (point.longitude > 180) {
            point.longitude = -180 + (point.longitude - 180);
        }
        if (point.longitude < -180) {
            point.longitude = 180 + (point.longitude + 180);
        }
        
        return point;
    }
    
    /**
     * Returns the distance between tow points
     *
     * @param p1
     * @param p2
     * @param unit
     * @return
     */
    public static double getDistanceBetweenPoints(Point p1, Point p2, String unit) {
        double theta = p1.longitude - p2.longitude;
        double dist = Math.sin(deg2rad(p1.latitude)) * Math.sin(deg2rad(p2.latitude)) + Math.cos(deg2rad(p1.latitude))
        * Math.cos(deg2rad(p2.latitude)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        if (unit.equals("km")) {
            dist = dist * 1.609344;
        } else if (unit.equals("miles")) {
            dist = dist * 0.8684;
        }
        return (dist);
    }


    public static double getDistance(double latitude, double longitude, double latitude1, double longitude1, String unit) {
        Point meOne = new Point(latitude, longitude);
        Point youOne = new Point(latitude1, longitude1);
        double distance = GeoLocationUtils.getDistanceBetweenPoints(meOne, youOne, unit);
        return distance;

    }
}



