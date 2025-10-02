# Klasse som representerer hele boksamlingen 
class Bibliotek:
    def __init__(self):
        # Lager en tom liste for å lagre bokobjektene
        self.bøker = []

    # legger til en ny bok i biblioteket
    def legg_til_bok(self, bok):
        self.bøker.append(bok)
        print(f"Bok lagt til: {bok.tittel}")

    # Fjerner en bok baser på tittelen
    def fjern_bok(self, tittel):
        for bok in self.bøker:
            if bok.tittel == tittel:
                self.bøker.remove(bok)
                print(f"Bok fjernet: {tittel}")
                return
        print(f"Bok ikke funnet: {tittel}")
        
    # Setter status på boken til utlånt
    def lån_ut(self, tittel):
        for bok in self.bøker:
            if bok.tittel == tittel:
                if not bok.utlånt:
                    bok.utlånt = True
                    print(f"Bok lånt ut: {tittel}")
                    return
                else:
                    print(f"Boka er allerede utlånt: {tittel}")
                    return
        print(f"Bok ikke funnet: {tittel}")
        
    # Setter status på boken til levert inn 
    def lever_inn(self, tittel):
        for bok in self.bøker:
            if bok.tittel == tittel:
                if bok.utlånt:
                    bok.utlånt = False
                    print(f"Bok levert inn: {tittel}")
                    return
                else:
                    print(f"Boka var ikke utlånt: {tittel}")
                    return
        print(f"Bok ikke funnet: {tittel}")

    #Skriver ut alle bøkene
    def skriv_ut_bøker(self):
        if not self.bøker:
            print("Biblioteket er tomt.")
        else:
            print("Bøker i biblioteket:")
            for bok in self.bøker:
                print(" -", bok)
