# OTP-Java
Programozási feladat
Írjon egy java alkalmazást, amely két online webshop vásárlóit, és azok kifizetéseit kezeli!
Készítsen egy szolgáltatást, amely megvalósítja az alábbi funkciókat:
Adott két csv állomány, amely két webshop vásárlóit, és azok kifizetéseit tartalmazzák.

## A customer.csv mezői:
- Webshop azonosító **(*int*)**
- Ügyfél azonosító (Webshop-on belül egyedi!) **(*int*)**
- Ügyfél neve **(*string*)**
- Ügyfél címe **(*string*)**

## A payments.csv mezői:
- Webshop azonosító **(*int*)**
- Ügyfél azonosító (Webshop-on belül egyedi!) **(*int*)**
- Fizetés módja ( 'card' | 'transfer' ) Kártyás fizetés, vagy banki átutalás (*boolean*)
- Összeg (HUF) (*int*)
- Bankszámlaszám, amennyiben banki átutalás történt if(payment->utalás) **(*int*)**
- Kártyaszám, amennyiben kártyás fizetés történt if(payment->kártya) **(*int*)**
- Fizetés dátuma **(*DateTime*)**

# Feladat
Olvassuk be az adatokat, és tároljuk el a memóriában.
• Határozzunk meg ellenőrzéseket, és kezeljük le az állományban található hibás sorokat: egy
application.log file-ba logoljuk le a hibaüzenetet, és a hibát tartalmazó sorokat! Ezen sorok **ne**
kerüljenek beolvasásra!

• A beolvasott adatok alapján készítsünk riportot, amely soronként összesítve tartalmazza egy ügyfél
összes vásárlásának összegét. 
A file az alábbi mezőket tartalmazza:
**NAME, ADDRESS, vásárlás összesen**
Az eredmény mentsük le a ***report01.csv*** file-ba!

• Az előző eredmény listából válasszuk ki a két legtöbb pénzt költött vásárlót, és az adatait mentsük le
a fenti struktúrában, a ***top.csv*** file-ba!

• A beolvasott adatok alapján készítsünk riportot, amely soronként összesítve tartalmazza az egyes
webshopok bevételeit. 
A file az alábbi mezőket tartalmazza:
**WEBSHOP, kártyás vásárlások összege, átutalásos vásárlások összege**
Az eredmény mentsük le a ***report02.csv*** file-ba!

*Az alkalmazáshoz nem szükséges UI felületet, és adatbázist használni, és nem elvárás third party
keretrendszerek, vagy library-k használata.
Amennyiben a hallgató szeretne ilyen eszközöket használni, az is megengedett.*

Az értékelés fő szempontjai:
 - az adatmodell tervezése,
 - a megfelelően strukturált programkód,
 - a program hibamentes működése.
