# language: de
Funktionalität: Zeigt Guthaben an

Szenario: Zeigt das Guthaben an, nachdem Münzen eingeworfen wurden
Angenommen der Automat ist frisch gestartet
Wenn man 3 Münzen a 50 Cent einwirft
Dann wird "1,50 €" angezeigt

Szenario: Zeigt das summierte Guthaben an, nachdem Münzen unterschiedlichen Wertes eingeworfen wurden
  Angenommen der Automat ist frisch gestartet
  Wenn man 1 Münzen a 50 Cent einwirft
  Wenn man 1 Münzen a 100 Cent einwirft
  Wenn man 2 Münzen a 200 Cent einwirft
  Dann wird "5,50 €" angezeigt
