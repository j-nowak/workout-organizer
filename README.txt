Naszym projektem jest system, który ułatwi śledzenie swoich trenignów i postępów na siłowni. Użytkownik ma podgląd na postępy i osiągnięcia swoich znajomych. Dodatkowo, w celu motywacji przyjaciół, ma on możliwość polubienia wpisów znajomego.

Głównym elementem aplikacji jest tworzenie wpisów treningowych. Dany wpis zawiera informacje o czasie rozpoczęcia i zakończenia treningu, siłowni, na której ten trening się odbył oraz listę ćwiczeń, które wykonał użytkownik wraz z liczbą serii, liczbą powtórzeń oraz obciążeniem. Dodatkowo do takiego wpisu można dołączyć notatkę z informacją o tym treningu (np. "Ło ku***, rekord!")

Użytkownik może logować się do aplikacji przy użyciu swojego loginu lub adresu email. W bazie przechowywany jest hash jego hasła w celu zwiększenia bezpieczeństwa. Dane użytkownika takie jak imię, nazwisko, data urodzenia przechowywane są w tabeli users.

Podobnie jak w Facebook'u, użytkownicy mają swoich znajomych (informacje o tym, kto z kim jest znajomym, przechowywane są w tabeli friendships) i mogą zapraszać innych aby zostali ich znajomymi (dane przechowywane w tabeli frienship_requests).

Na stronie głownej wyświetlane są najnowsze wpisy znajomych z informacją o tym kiedy i na której siłowni trenowali oraz jakie partie mięśni zrobili podczas tego treningu (dane te są wyliczane przy użyciu widoku detailed_news). Wszystkie te wpisy można likować (dany użytkownik może polubić pewien wpis tylko raz).

Ćwiczenia są głównym elementem treningu. Są one wyświetlane w atlasie ćwiczeń. Dane ćwiczenie może posiadać opcjonalny opis lub filmik. Dodatkowo dostępna jest lista mięśni które sa używane podczas wykonywania danego ćwiczenia. Dzięki temu użytkownik może filtrować ćwicznienia po partiach mięśniowych. Ponadto każdy użytkownik ma możliwość wystawienia oceny danemu ćwiczeniu oraz zobaczenia średnią ocenę innych użytkowników. Dzieki temu uzytkownik może wybrać najlepsze ćwiczenia.

W systemie znajduje sie również baza siłowni. Każda siłownia posiada adres, aby użytkownik mógł ją łatwo zlokalizować. Opcjonalnie, jeśli dana siłownia posiada wprowadzone współrzędne geograficzne, to jej położenie jest wyświetlane z wykorzystaniem GoogleMaps API. Korzystając z geolokalizacji HTML5, mapa centrowana jest na aktualne położenie usera, aby mógł jak najszybciej odnaleźć najbliżsżą siownię i rozpocząć trening. Podobnie jak w przypadku ćwiczeń, każdą siłownię mozna ocenić.


Wymagania:
-Java 8,
-PostgreSQL JDBC Driver for Java 8 (biblioteka musi się znaleźć w folderze lib)
https://jdbc.postgresql.org/download/postgresql-9.4-1200.jdbc41.jar

-stworzyć plik application.conf według wzoru (adres bazy, usera i hasło):

#begin
application.secret="/vqXV_IjArDXQ`BMmk__m[6:5p<=RhOfLej2GhmoX<w2baKdrHsP@L5maKlP[7fy"
application.langs="en"

ebean.default = ["models.*"]

play.http.requestHandler = "handler.HttpRequestHandlerImpl"

db.default.driver=org.postgresql.Driver
db.default.url="jdbc:postgresql://localhost/baza_workouts"
db.default.user="uzytkownik"
db.default.password="haslo"

logger.root=ERROR

logger.play=INFO
logger.application=DEBUG
#end

Jak postawić:
git clone https://github.com/j-nowak/workout-organizer.git
# Postawić bazę z create.sql
cd workout-organizer/src
mkdir lib # tutaj wrzucić bibliotekę postgresa
cd conf # tutaj wrzucić plik application.conf
cd ..
./activator
# Po uruchomieniu kosnoli:
run
# Serwer powinien działać i nasłuchuje pod adresem localhost:9000


