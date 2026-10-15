# language: de
Funktionalität: Zeigt Guthaben an

Szenario: Zeigt das Guthaben an, nachdem Münzen eingeworfen wurden
  Angenommen der Automat ist frisch gestartet
  Wenn ich eine 50 ct Münze einwerfe
  Und ich eine 50 ct Münze einwerfe
  Und ich eine 50 ct Münze einwerfe
  Dann wird "1,50 €" angezeigt

Szenario: Zeigt das summierte Guthaben an, nachdem Münzen unterschiedlichen Wertes eingeworfen wurden
  Angenommen der Automat ist frisch gestartet
  Wenn ich eine 50 ct Münze einwerfe
  Und ich eine 1,00 € Münze einwerfe
  Und ich eine 2,00 € Münze einwerfe
  Und ich eine 2,00 € Münze einwerfe
  Dann wird "5,50 €" angezeigt
