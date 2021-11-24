1.	**Название проекта** : Постановка машины на учёт в ГИБДД.
2.	**Описание проекта**  :
      В проекте реализуется симуляция процесса постановки машины на учёт в ГИБДД множеством людей. Главными местами в этом процессе являются смотровая площадка, обычная стоянка и здание ГИБДД. Порядок постановки на учёт выглядит следующим образом. Человек приезжает на обычную стоянку на автомобиле, который он хочет зарегистрировать (поставить на учёт). Далее он идёт пешком в здание ГИБДД, в котором идёт в приёмное окно для новоприбывших граждан (всего их два вида - одно для новоприбывших граждан, другое - для граждан, которые уже прошли осмотр машины). В этом окне он отдаёт документы сотруднику ГИБДД, получает от него разрешение на осмотр машины. Далее гражданин с обычной стоянки переезжает на смотровую площадку, на ней его машину осматривает полицейский, даёт разрешение на регистрацию автомобиля. После этого гражданин возвращает машину на обычную стоянку, идёт в здание ГИБДД и во втором окне получает свидетельство о регистрации - машина успешно поставлена на учёт. Человек уезжает на своей машине.
3.	**Независимые потоки** : 
      Независимыми потоками в проекте являются люди, ставящие свои автомобили на учёт.
4.	**Разделяемый среди потоков ресурс**: 
      Разделяемыми ресурсами среди потоков будут приёмные окна в здании ГИБДД, место осмотра на смотровой площадке и места на обычной стоянке.
5.	**Остановка потоков по событию**:
      Потоки могут быть остановлены по следующим событиям: завершение симуляции по сигналу пользователя, постановка симуляции на паузу.
6.	**Способ управления проектом**: 
      Проект будет управляться при помощи следующих устройств ввода: компьютерная мышь, клавиатура. Пользователь не будет иметь непосредственного влияния на ход симуляции, но он будет иметь возможность выбирать начальные параметры: количество людей, их скорость передвижения. 