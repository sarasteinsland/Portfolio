from konto import BankKonto
from meny import Menu

# Hovedprogrammet som viser menyen og utfører brukerens valg
def hovedprogram():
    konto = None
    meny = Menu()
    
    # Legger til alternativer i menyen
    meny.legg_til_valg("Opprett en ny konto")
    meny.legg_til_valg("Sett inn penger")
    meny.legg_til_valg("Ta ut penger")
    meny.legg_til_valg("Legg til rente")
    meny.legg_til_valg("Vis saldo")
    meny.legg_til_valg("Avslutt")

    # Løkke som fortsetter til brukeren velger å avslutte
    while True:
        valg = meny.hent_input() # Henter brukrens menyvalg

        # Oppretter ny konto
        if valg == 1:
            konto = BankKonto()
            print("Ny konto opprettet!")
        
        # Setter inn penger
        elif valg == 2:
            if konto:
                beløp = float(input("Skriv inn beløpet du vil sette inn: "))
                konto.sett_inn(beløp)
            else:
                print("Du må opprette en konto først.")
        # Tar ut penger
        elif valg == 3:
            if konto:
                beløp = float(input("Skriv inn beløpet du vil ta ut: "))
                konto.ta_ut(beløp)
            else:
                print("Du må opprette en konto først.")
        # Legger til rente
        elif valg == 4:
            if konto:
                konto.legg_til_rente()
            else:
                print("Du må opprette en konto først.")
        # Viser saldo
        elif valg == 5:
            if konto:
                print(f"Aktuell saldo: {konto.hent_saldo():.2f} kr")
            else:
                print("Du må opprette en konto først.")
        # Avslutter programmet
        elif valg == 6:
            print("Takk for at du brukte banken vår.")
            break
# Starter hovedprogrammet
if __name__ == "__main__":
    hovedprogram()
