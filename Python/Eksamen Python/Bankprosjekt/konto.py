class BankKonto:
    # Konstruktør, starter opp en ny bankkonto med saldo 0kr
    def __init__(self):
        self.saldo = 0.0
        
    # Setter inn penger på kontoen
    def sett_inn(self, beløp):
        if beløp > 0:
            self.saldo += beløp
            print(f"{beløp} kr er satt inn.")
        else:
            print("Ugyldig beløp.")
    # Tar ut penger fra kontoen, men sjekker først om det er nok penger
    def ta_ut(self, beløp):
        if beløp > self.saldo:
            print("Ikke nok penger på konto.")
        elif beløp <= 0:
            print("Ugyldig beløp.")
        else:
            self.saldo -= beløp
            print(f"{beløp} kr er tatt ut.")
            
    # Legger til renter på det bruker har på konto
    def legg_til_rente(self, rente_prosent=5.0):
        rente = self.saldo * (rente_prosent / 100)
        self.saldo += rente
        print(f"Rente på {rente:.2f} kr lagt til.")
        
    # Returnerer nåværende saldo
    def hent_saldo(self):
        return self.saldo
