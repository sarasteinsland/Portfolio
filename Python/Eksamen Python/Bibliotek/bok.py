# Klasse som representerer en enkel bok i biblioteket
class Bok:
    def __init__(self, tittel, forfatter, antall_sider):
        # En enkel bok sin tittel, forfatter og sideantall
        self.tittel = tittel
        self.forfatter = forfatter
        self.antall_sider = antall_sider
        self.utlånt = False # Justeres til True hvis boken er utlånt

    def __str__(self):
        # Tekst som vises når boken skrives ut
        status = "Utlånt" if self.utlånt else "Tilgjengelig"
        return f"'{self.tittel}' av {self.forfatter} ({self.antall_sider} sider) - {status}"
