# language: de
Funktionalität: Bier kostet mehr

Als cleverer Automatenbetreiber möchte ich Bier für 2,00 € verkaufen,
damit ich an durstigen Studenten mehr verdiene.

  Szenario: Der Preis von Bier ist 2,00 €
    Angenommen der Automat ist frisch gestartet
    Dann ist der Preis von der Bier erkennbar

  Szenario: Mit weniger als 2,00 € fällt kein Bier
    Angenommen der Automat ist frisch gestartet
    Wenn ich eine 1 € Münze einwerfe
    Und ich Bier wähle
    Dann liegt kein Getränk im Ausgabefach
    Und der Automat meldet "Zu wenig Geld"
    Und bleibt das Guthaben bei "1,00 €"

  Szenario: Mit genau 2,00 € fällt Bier
    Angenommen der Automat ist frisch gestartet
    Wenn ich eine 2 € Münze einwerfe
    Und ich Bier wähle
    Dann liegt eine Dose Bier im Ausgabefach
    Und bleibt das Guthaben bei "0,00 €"

  Szenario: Mit mehr als 2,00 € fällt Bier und Restguthaben bleibt
    Angenommen der Automat ist frisch gestartet
    Wenn ich eine 2 € Münze einwerfe
    Und ich eine 50 ct Münze einwerfe
    Und ich Bier wähle
    Dann liegt eine Dose Bier im Ausgabefach
    Und bleibt das Guthaben bei "0,50 €"
