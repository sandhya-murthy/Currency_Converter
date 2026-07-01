# Currency Converter Application

## **Project Overview**

The Currency Converter Application is a native Android application developed using **Java** in **Android Studio**. It allows users to convert an amount from one currency to another using **real-time exchange rates** obtained from a public REST API. The application features a clean and user-friendly interface with currency selection, instant conversion, exchange rate display, currency swapping, input validation, and proper error handling.

---

## **Objective**

The objective of this project is to develop an Android application that demonstrates Android application development concepts such as user interface design, REST API integration, JSON parsing, and real-time currency conversion using Java.

---

## **Features**

- Real-time currency conversion using live exchange rates.
- Enter any amount for conversion.
- Select source currency from a dropdown list.
- Select target currency from a dropdown list.
- Swap source and target currencies with a single click.
- Display the exchange rate used for conversion.
- Display the converted amount instantly.
- Validate empty and invalid inputs.
- Handle internet connectivity and API errors gracefully.
- Simple and responsive Material Design user interface.

---

## **Technologies Used**

- **Programming Language:** Java
- **IDE:** Android Studio
- **User Interface:** XML Layouts
- **Networking Library:** Retrofit
- **JSON Parsing:** Gson Converter
- **API:** Exchange Rate API
- **Minimum SDK:** Android 7.0 (API 24)

---

## **Project Structure**

```text
CurrencyConverter/
│
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   ├── MainActivity.java
│   │   │   │   └── api/
│   │   │   │       ├── ApiClient.java
│   │   │   │       ├── CurrencyApi.java
│   │   │   │       └── CurrencyResponse.java
│   │   │   ├── res/
│   │   │   │   └── layout/
│   │   │   │       └── activity_main.xml
│   │   │   └── AndroidManifest.xml
│
└── README.md
```

---

## **Working**

1. The user enters the amount to be converted.
2. The user selects the source currency.
3. The user selects the target currency.
4. The application requests the latest exchange rates from the Exchange Rate API.
5. The retrieved exchange rate is used to calculate the converted amount.
6. The converted value and the exchange rate are displayed on the screen.
7. Users can swap the selected currencies using the swap button.
8. The application validates user input and displays appropriate error messages for invalid entries or network failures.

---

## **Screenshots**

<img width="360" height="787" alt="Screenshot 2026-07-02 000300" src="https://github.com/user-attachments/assets/4a9df35c-76b8-49d7-b780-f941b6d54bb8" />
<img width="360" height="787" alt="Screenshot 2026-07-02 000319" src="https://github.com/user-attachments/assets/410b773a-2294-42ca-aca6-13e7693aa98f" />


---

## **Future Enhancements**

- Searchable currency list.
- Conversion history.
- Favorite currencies.
- Offline caching of exchange rates.
- Dark mode support.
- Exchange rate trend visualization.
- Multi-language support.

---

## **Learning Outcomes**

This project provided practical experience in:

- Android application development using Java.
- Designing user interfaces with XML layouts.
- Integrating REST APIs using Retrofit.
- Parsing JSON responses using Gson.
- Implementing event handling in Android.
- Working with Material Design components.
- Handling user input validation.
- Managing API and network-related errors.
- Processing real-time data within an Android application.

---

## **Output**

The application successfully performs real-time currency conversion by retrieving the latest exchange rates from a public REST API and presenting accurate conversion results through an intuitive Android interface.

---

## **Author**

**Sandhya**
