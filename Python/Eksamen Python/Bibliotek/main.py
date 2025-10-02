# Importerer klassene fra de to andre filene
from bok import Bok
from bibliotek import Bibliotek

# Funskjon for å teste systemet
def test_bibliotek():
    bibliotek = Bibliotek()

    # Lager noen bøker
    bok1 = Bok("Harry Potter", "J.K.Rowling", 328)
    bok2 = Bok("Game Of Thrones", "George R.R. Martin", 471)
    bok3 = Bok("Snømannen", "Jo Nesbø", 446)

    # Legger bøkene inn i bibliotekets samling
    bibliotek.legg_til_bok(bok1)
    bibliotek.legg_til_bok(bok2)
    bibliotek.legg_til_bok(bok3)

    # Simulerer bruker input i test filen.
    bibliotek.lån_ut("Harry Potter")
    bibliotek.lever_inn("Harry Potter")
    bibliotek.lån_ut("Matilda") # Ukjent bok, gir ut en feilmelding
    bibliotek.fjern_bok("Game Of Thrones")
    bibliotek.skriv_ut_bøker()

# Starter testfilen
if __name__ == "__main__":
    test_bibliotek()
