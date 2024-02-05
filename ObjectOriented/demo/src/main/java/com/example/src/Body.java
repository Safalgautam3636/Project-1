package com.example.src;

import java.lang.Math;
public class Body {
    protected String Name;
    protected double Diameter;
    protected double Circumference;
    protected double volume;

    Body(String Name, double Diameter, double Circumference) {
        
        this.Name = Name;
        this.Diameter = Diameter;
        this.Circumference = Circumference;
        if (this.Diameter == 0.0) {
            this.Diameter = calculateDiameter(Circumference);
        } else if (this.Circumference == 0.0) {
            this.Circumference = calculateCircumferece(Diameter);
        }
        this.volume = computeVolume(Diameter);
    }
    
    public String getName() {
        return this.Name;
    }
    
    public double getDiameter() {
        return this.Diameter;
    }
    
    public double getCircumference() {
        return this.Circumference;
    }
    
    public double getVolume() {
        return this.computeVolume(this.Diameter);
    }

    public double calculateDiameter(double circumference) {
        // 2 * pi * r
        return this.Diameter=(double) circumference / Math.PI;
        //System.out.println(Diameter);
    }

    public double calculateCircumferece(double diameter) {
        return this.Circumference=(double) diameter * Math.PI;      
    }

    public double computeVolume(double diameter) {
        return (1.0 / 6.0) * Math.PI * Math.pow(this.Diameter, 3);
        
    }
}

class Planet extends Body {

    double DistanceFromSun;
    double OrbitalPeriod;
    Moon[] Moons;

    Planet(String Name, double Diameter, double Circumference, double DistanceFromSun,double OrbitalPeriod,Moon[]Moons){
        super(Name, Diameter, Circumference);
        
        this.Moons = Moons;
        this.DistanceFromSun = DistanceFromSun;
        this.OrbitalPeriod = OrbitalPeriod;
        if (DistanceFromSun != 0.0) {
            getOrbitalIfDistance(DistanceFromSun);
        }
        else if (OrbitalPeriod != 0.0) {
            getDistanceIfOrbital(OrbitalPeriod);
        }
        super.volume = super.computeVolume(Diameter);
    }
    public Moon[] getMoons() {
        return this.Moons;
    }

    public double getDistanceFromSum() {
        return this.DistanceFromSun;
    }

    public double getOrbitalPeriod() {
        return this.OrbitalPeriod;
    }
    public void getDistanceIfOrbital(double OrbitalPeriod) {
        this.DistanceFromSun = Math.cbrt(Math.sqrt(OrbitalPeriod));
    }

    public void getOrbitalIfDistance(double DistanceFromSun) {
        this.OrbitalPeriod = Math.sqrt(Math.pow(DistanceFromSun, 3));
    }
}

class Moon extends Body {
    Moon(String Name, double Diameter, double Circumference){
        super(Name, Diameter, Circumference);
    }
}

class Sun extends Body {
    Planet[] Planets;
    boolean isVolGreaterOrLess;
    Sun(String Name, double Diameter, double Circumference,Planet[] Planet,boolean isVolGreaterOrLess){
        super(Name, Diameter, Circumference);
        this.Planets = Planet;
        this.isVolGreaterOrLess = isVolGreaterOrLess;
    }

    public Planet[] getPlanets() {
        return this.Planets;
    }

}