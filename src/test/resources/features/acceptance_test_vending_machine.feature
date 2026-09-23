# language: de
Funktionalität: Freies Getränk
  Der erste Schritt: ein Getränk ohne Bezahlung.
  Wer ein Fach wählt, bekommt die Dose.

  Szenario: Für jedes Getränk wird der Preis angezeigt
    Angenommen der Automat ist frisch gestartet
    Dann wird auf dem Display unter jedem Getränk der zugehörige Preis angezeigt

  Szenario: Eingeworfene 50 Cent werden als Guthaben angezeigt
    Angenommen der Automat ist frisch gestartet
    Wenn ich 1 Münze zu je 50 Cent einwerfe
    Dann habe ich 50 Cent Guthaben

  Szenario: Mehrere eingworfene Münzen werden als Guthaben angezeigt
    Angenommen der Automat ist frisch gestartet
    Wenn ich 1 Münze zu je 50 Cent einwerfe
    Wenn ich 2 Münze zu je 100 Cent einwerfe
    Dann habe ich 250 Cent Guthaben

  Szenario: Man muss genug Geld haben um ein Getränk kaufen zu können
    Angenommen der Automat ist frisch gestartet
    Und ich habe 100 Cent Guthaben
    Wenn ich Cola wähle
    Dann liegt eine Dose Cola im Ausgabefach
    Dann habe ich 0 Cent Guthaben

  Szenario: Ich wähle Cola aus aber ich habe keine Guthaben
    Angenommen der Automat ist frisch gestartet
    Wenn ich Cola wähle
    Dann liegt keine Dose Cola im Ausgabefach