# language: de
Funktionalität: Wechselgeld

  Als ehrlicher Automatenbetreiber möchte ich Wechselgeld herausgeben, wenn jemand Abbruch drückt.

  Szenario: Abbruch zahlt gesamtes Guthaben aus
    Angenommen der Automat ist frisch gestartet
    Und ich eine 1,00 € Münze einwerfe
    Und ich eine 50 ct Münze einwerfe
    Wenn ich Abbruch drücke
    Dann liegen 2 Münzen in der Münzrückgabe
    Und bleibt das Guthaben bei "0,00 €"

  Szenario: Abbruch ohne Guthaben
    Angenommen der Automat ist frisch gestartet
    Wenn ich Abbruch drücke
    Dann liegen keine Münzen in der Münzrückgabe
    Und bleibt das Guthaben bei "0,00 €"

  Szenario: Münzrückgabe leeren
    Angenommen der Automat ist frisch gestartet
    Und ich eine 1,00 € Münze einwerfe
    Und ich Abbruch drücke
    Und ich die Münzrückgabe leere
    Dann liegen keine Münzen in der Münzrückgabe
