import random

class Ordspill:
    def __init__(self, ord_fil):
        self.ord_fil = ord_fil
        self.valgt_ord = ""
        self.gjettede_bokstaver = set()
        self.maks_feil = 0
        self.feil = 0

    def last_inn_ord(self):
        # Laster inn ord fra filen og lager en liste
        try:
            with open(self.ord_fil, 'r', encoding='utf-8') as fil:
                ord_liste = [linje.strip().lower() for linje in fil if linje.strip()]
            return ord_liste
        except FileNotFoundError:
            print(f"Feil: Kunne ikke finne filen '{self.ord_fil}'.")
            return []

    def velg_tilfeldig_ord(self, ord_liste):
        # Programmet velger et tilfeldig ord fra tekstfilen
        return random.choice(ord_liste)

    def vis_ord(self):
        # Viser bokstavene som er gjettet riktig og med _ for de som er igjen
        return ' '.join([bokstav if bokstav in self.gjettede_bokstaver else '_' for bokstav in self.valgt_ord])

    def er_ord_gjettet(self):
        # Sjekker om hele ordet er riktig gjettet
        return all(bokstav in self.gjettede_bokstaver for bokstav in self.valgt_ord)

    def gjett_bokstav(self, bokstav):
        # Sjekker det brukeren gjetter og ser om det allerede er gjettet før
        if bokstav in self.gjettede_bokstaver:
            print("Du har allerede gjettet denne bokstaven.")
            return

        self.gjettede_bokstaver.add(bokstav)

        if bokstav not in self.valgt_ord:
            self.feil += 1
            print("Beklager, bokstaven er ikke i ordet.")
        else:
            print("Riktig!")

    def spill(self):
        # Starter spillet
        ord_liste = self.last_inn_ord()
        if not ord_liste:
            return
        
        # Velger et filfeldig 0rd og tilbakestiller spill-variabler
        self.valgt_ord = self.velg_tilfeldig_ord(ord_liste)
        self.maks_feil = len(self.valgt_ord)
        self.feil = 0
        self.gjettede_bokstaver.clear()
        
        
        # Informerer spilleren om antall bokstaver og forsøk
        print(f"\nOrdet du skal gjette har {len(self.valgt_ord)} bokstaver.")
        print(f"Du har {self.maks_feil} feilforsøk tilgjengelig.\n")

        # Hovedløkken til spillet
        while self.feil < self.maks_feil:
            print(self.vis_ord()) # Viser nåværende tilstand av ordet
            print(f"Du har {self.maks_feil - self.feil} gjetting(er) igjen.")
            gjett = input("Gjett en bokstav: ").lower()

            # Sjekker at input er en gyldig bokstav
            if len(gjett) != 1 or not gjett.isalpha():
                print("Ugyldig input. Skriv inn én bokstav.")
                continue

            # Behandler gjettingen
            self.gjett_bokstav(gjett)
            
            # Sjekker om hele ordet er gjettet
            if self.er_ord_gjettet():
                print(f"\nDu gjettet ordet => \"{self.valgt_ord}\"")
                print("Gratulerer, du vant!")
                return
        
        # Om brukeren har brukt opp alle forsøk får de opp melding
        print("\nBeklager, du tapte.")
        print(f"Ordet var => \"{self.valgt_ord}\"")

# Starter programmet
if __name__ == "__main__":
    spill = Ordspill("Ordspill.txt")
    spill.spill()
