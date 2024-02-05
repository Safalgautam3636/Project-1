package com.example.src;

import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Main {
    @SuppressWarnings("removal")
    public static void main(String[] args) {
        String file = "ObjectOriented/demo/src/main/java/com/example/src/input.json";

        try {
            Object obj = new JSONParser().parse(new FileReader(file));
            JSONObject planetObj = (JSONObject) obj;
            JSONArray planetsArray = (JSONArray) planetObj.get("Planets");
            double volumeSum = 0.0;
            Planet[] planets = new Planet[planetsArray.size()];
            Moon[] copyMoon=null;
            int j = 0;
            for (Object planetObject : planetsArray) {
                JSONObject planet = (JSONObject) planetObject;
                JSONArray moons = (JSONArray) planet.get("Moons");
                Moon[] moonArray = null;
                if (moons != null) {
                    moonArray = new Moon[moons.size()];
                }

                int i = 0;
                if (moons != null && moons.size() > 0) {
                    for (Object moon : moons) {
                        String moonName;
                        double moonDiameter = 0.0;
                        double moonCircumference = 0.0;
                        JSONObject moonObj = (JSONObject) moon;
                        moonName = (String) moonObj.get("Name");
                        if (moonObj.get("Diameter") != null) {
                            String diameter = moonObj.get("Diameter").toString();
                            moonDiameter = new Double(diameter);
                        }
                        if (moonObj.get("Circumference") != null) {
                            String circumference = moonObj.get("Circumference").toString();
                            moonCircumference = new Double(circumference);
                        }

                        Moon moonObject = new Moon(moonName, moonDiameter, moonCircumference);
                        moonArray[i++] = moonObject;

                    }
                    copyMoon = moonArray;
                }

                String planetName = (String) planet.get("Name");
                double planetDiameter = 0.0;
                double planetCircumference = 0.0;
                double distanceFromSun = 0.0;
                double orbitalPeriod = 0.0;
                if (planet.get("DistanceFromSun") != null) {
                    distanceFromSun = new Double(planet.get("DistanceFromSun").toString());
                }
                if (planet.get("OrbitalPeriod") != null) {
                    orbitalPeriod = new Double(planet.get("OrbitalPeriod").toString());
                }
                if (planet.get("Diameter") != null) {
                    planetDiameter = new Double(planet.get("Diameter").toString());
                }
                if (planet.get("Circumference") != null) {
                    planetCircumference = new Double(planet.get("Circumference").toString());
                }

                Planet planetObje = new Planet(planetName, planetDiameter, planetCircumference, distanceFromSun,
                        orbitalPeriod, moonArray);
                volumeSum += planetObje.volume;
                planets[j++] = planetObje;
                // volsum>sun

            }

            String bodyName = (String) planetObj.get("Name");
            double bodyDiameter = 0.0;
            double bodyCircumference = 0.0;
            if (planetObj.get("Diameter") != null) {
                String diameter = planetObj.get("Diameter").toString();
                bodyDiameter = new Double(diameter);
            }
            if (planetObj.get("Circumference") != null) {
                String circumference = planetObj.get("Circumference").toString();
                bodyCircumference = new Double(circumference);
            }
            Sun sun = new Sun(bodyName, bodyDiameter, bodyCircumference, planets, false);
            sun.isVolGreaterOrLess = volumeSum > sun.volume;
            // System.out.println(sun.isVolGreaterOrLess);
            // System.out.println(sun.volume);
            System.out.println(
                    "The Output of the Project-1 of Programming Languages using Object Oriented Programming..");
            System.out.println("Sun: " + sun.Name);
            System.out.println("Diameter: " + sun.Diameter + " km");
            System.out.println("Circumference: " + sun.Circumference + " km");
            System.out.println("Volume greater than sum of all?  " + sun.isVolGreaterOrLess);

            // planets
            int itr1 = 1;
            for (Planet p : planets) {
                System.out.println();
                System.out.println("Planet number-"+itr1++);
                System.out.println("Planet: " + p.Name);
                System.out.println("Distance from Sun: " + p.DistanceFromSun +" au");
                System.out.println("Orbital period: " + p.OrbitalPeriod +" yr");
                System.out.println("Diameter: " + p.Diameter + " km");
                System.out.println("Circumference: " + p.Circumference + " km");
                int itr2 = 1;
                for (Moon moon : copyMoon) {
                    System.out.println("Moon number-" + itr2++);
                    System.out.println("Moon: " + moon.Name);
                    System.out.println("Diameter: " + moon.Diameter+" km");
                    System.out.println("Circumference: " + moon.Circumference + " km");
                    
                }
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
