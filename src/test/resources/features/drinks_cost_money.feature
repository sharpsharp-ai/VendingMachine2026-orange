# language: de
Funktionalität: Getränke kosten Geld

  Szenario: Guthaben reicht zum Kauf
    Angenommen der Automat ist frisch gestartet
    Wenn ich eine 1,00 € Münze einwerfe
    Und ich eine 50 ct Münze einwerfe
    Und ich Cola wähle
    Dann liegt eine Dose Cola im Ausgabefach
    Und bleibt das Guthaben bei "0,50 €"

  Szenario: Guthaben reicht nicht
    Angenommen der Automat ist frisch gestartet
    Wenn ich eine 50 ct Münze einwerfe
    Und ich Cola wähle
    Dann liegt kein Getränk im Ausgabefach
    Und der Automat meldet "Zu wenig Geld"
