import json
import math


def calculate_diameter(circumference):
    return circumference / math.pi


def calculate_circumference(diameter):
    return diameter * math.pi


def compute_volume(diameter):
    return (1.0 / 6.0) * math.pi * math.pow(diameter, 3)


def distance_from_orbital(orbital):
    return math.cbrt(math.sqrt(orbital))


def contains(obj, attr):
    if not obj.get(attr):
        return False
    return True


def handle_dia_cir(to, fro):
    to["Diameter"] = fro.get("Diameter")
    to["Circumference"] = fro.get("Circumference")
    if not contains(to, "Diameter"):
        to["Diameter"] = calculate_diameter(fro.get("Circumference"))
    if not contains(to, "Circumference"):
        to["Circumference"] = calculate_circumference(fro.get("Diameter"))


def handle_dis_orbit(to, fro):
    to["OrbitalPeriod"] = fro.get("OrbitalPeriod")
    to["DistanceFromSun"] = fro.get("DistanceFromSun")
    if not contains(to, "OrbitalPeriod"):
        to["OrbitalPeriod"] = orbital_from_distance(fro.get("DistanceFromSun"))
    if not contains(to, "DistanceFromSun"):
        to["DistanceFromSun"] = distance_from_orbital(fro.get("OrbitalPeriod"))


def orbital_from_distance(distance):
    return math.sqrt(math.pow(distance, 3))


def build_object(raw):

    body = {}
    body["Name"] = raw.get("Name")
    handle_dia_cir(body, raw)
    body["VolumeSol"] = compute_volume(body["Diameter"])
    planets = []
    volumeSum = 0.0
    for planet in raw.get("Planets"):
        planet_obj = {}
        planet_obj["Name"] = planet.get("Name")
        handle_dia_cir(planet_obj, planet)
        handle_dis_orbit(planet_obj, planet)
        moons = []
        if planet.get("Moons"):
            for moon in planet.get("Moons"):
                moon_obj = {}
                moon_obj["Name"] = moon.get("Name")
                handle_dia_cir(moon_obj, moon)
                moons.append(moon_obj)
        planet_obj["Moons"] = moons
        volumeSum += compute_volume(planet_obj["Diameter"])

        planets.append(planet_obj)
    body["VolumeSum"] = volumeSum

    body["Planets"] = planets

    return body


def print_all(objects):
    print("PROGRAMMING LANGUAGES PROJECT-1 PROCEDURAL PROGRAMMING")
    print()
    print("Sun: ",objects.get("Name"))
    print("Diameter: ",objects.get("Diameter"), " km")
    print("Circumference: ", objects.get("Circumference"), " km")
    print("Volme Sum > Volume Sol ? ",objects.get("VolumeSum") > objects.get("VolumeSol"))
    i = 1
    for planet in objects.get("Planets"):
        print()
        print("Planet-", i)
        i += 1
        print("Planet",planet.get("Name"))
        print("Diameter: ", planet.get("Diameter"), " km")
        print("Circumference: ", planet.get("Circumference"), " km")
        print("DistanceFromSun: ", planet.get("DistanceFromSun"), " au")
        print("OrbitalPeriod: ", planet.get("OrbitalPeriod"), " yr")
        if planet.get("Moons"):
            j=0
            for moon in planet.get("Moons"):
                print("Moon-",j)
                j+=1
                print("Moon: ",moon.get("Name"))
                print("Diameter: ", moon.get("Diameter"), " km")
                print("Circumference: ", moon.get("Circumference"), " km")


# Opening JSON file
with open("/Users/safalgautam/Desktop/spring24/prog/procedural/input.json") as f:
    # Returns JSON object as a dictionary
    json_file = json.load(f)

    # Print details of celestial objects
    print_all(build_object(json_file))
