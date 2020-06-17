# FootballStandingService
Get standings of a team playing league football match using country name, league name and team name

API endpoint: /footballservice?action=get_standing&country=England&league=Championship&team=Fulham 

A successful API call will return the following details along with the team standing.
{
  "countryId": "41",
  "leagueId": "149",
  "teamId": "2639",
  "countryName": "England",
  "leagueName": "Championship",
  "teamName": "Fulham",
  "standingPosition": "3"
}
