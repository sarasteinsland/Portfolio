#Vi må importere string modulen for å få tilgang til bokstaver og tegnsetting.
import string

def er_palindrom(tekst):
    # Gjør alt til små bokstaver
    tekst = tekst.lower()
    # Fjerner alle tegn som ikke er bokstaver, slik som mellomrom og punktum
    tekst = ''.join(char for char in tekst if char in string.ascii_lowercase + 'æøå')
    # Sjekker om teksten er lik når den leses baklengs
    return tekst == tekst[::-1]

def hovedprogram():
    while True:
        # Vi valgte å legge til en liten meny ved hjelp av en løkke slik at man 
        # slipper å kjøre programmet på nytt hver gang man vil sjekke et ord
        print("\n--- PALINDROMSJEKKER ---")
        print("1: Skriv inn tekst")
        print("0: Avslutt programmet")
        valg = input("Velg et alternativ (0 eller 1): ")

        if valg == "0":
            print("Programmet avsluttes.")
            break
        elif valg == "1":
            bruker_input = input("Skriv inn et ord eller en setning: ")
            # Sender brukeren tilbake hvis et ord ikke skrives inn
            if not bruker_input.strip():
                print("Du må skrive inn noe for å sjekke.")
                continue
            # Kaller funksjonen
            if er_palindrom(bruker_input):
                print(f"'{bruker_input}' er et palindrom!")
            else:
                print(f"'{bruker_input}' er ikke et palindrom.")
        else:
            # 
            print("Ugyldig valg. Prøv igjen.")

# Starter programmet
if __name__ == "__main__":
    hovedprogram()
