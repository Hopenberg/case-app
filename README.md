# Case app - an exemplary app created for recruitment purposes
Two endpoints are exposed:
- NumberOfCasesByState - show number of cases with given state, parsed by type of the case and date period
- AddressesOfActiveSites - show addresses of active sites in case, parsed by state of the case

Data is pre-created in json form, resting in resources folder. Data is fed into database on startup of the app. Data is stored in volatile memory only during the app running.
