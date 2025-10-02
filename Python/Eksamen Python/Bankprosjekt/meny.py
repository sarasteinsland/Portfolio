class Menu:
    # Konstruktør, initialiserer en tom liste med menyvalg
    def __init__(self):
        self.valg = []

    # Legger til et nytt alternativ i menyen
    def legg_til_valg(self, tekst):
        self.valg.append(tekst)

    # Viser menyen og henter brukerens valg
    def hent_input(self):
        print("\n--- HOVEDMENY ---")
        # Skriver ut hvert alternativ med tilhørende nummer
        for i, tekst in enumerate(self.valg, start=1):
            print(f"{i} {tekst}")
        try:
            # Leser inn brukerens menyvalg som tall
            valg = int(input("Velg et alternativ: "))
            # Sjekker om valget er gyldig (innenfor menyens alternativer)
            if 1 <= valg <= len(self.valg):
                return valg
            else:
                print("Ugyldig valg. Prøv igjen.")
                return self.hent_input()
        except ValueError:
            # Fanger opp ugyldig input 
            print("Skriv inn et tall.")
            return self.hent_input()
