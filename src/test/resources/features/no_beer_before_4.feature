# language: de
Funktionalität: Kein Bier vor 16 Uhr

Als verantwortungsvoller Automatenbetreiber möchte ich vor 16:00 Uhr kein Bier verkaufen,
damit im Büro erst nach Feierabend getrunken wird.

  Szenario: Vor 16 Uhr fällt kein Bier
    Angenommen der Automat ist frisch gestartet
    Und es ist 15:59 Uhr
    Wenn ich Bier wähle
    Dann liegt kein Getränk im Ausgabefach
    Und der Automat meldet "Kein Bier vor 16"
    Und bleibt das Guthaben bei "0,00 €"

  Szenario: Ab 16 Uhr fällt Bier normal
    Angenommen der Automat ist frisch gestartet
    Und es ist 16:00 Uhr
    Und ich werfe eine 2,00 € Münze ein
    Wenn ich Bier wähle
    Dann liegt eine Dose Bier im Ausgabefach
    Und bleibt das Guthaben bei "0,00 €"

  Szenario: Vor 16 Uhr bleibt Guthaben
    Angenommen der Automat ist frisch gestartet
    Und es ist 15:59 Uhr
    Und ich werfe eine 2,00 € Münze ein
    Wenn ich Bier wähle
    Dann wird "2,00 €" angezeigt
    Und der Automat meldet "Kein Bier vor 16"
