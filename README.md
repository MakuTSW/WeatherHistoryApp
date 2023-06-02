# Weather History App

## API Reference

This API allows you to get today's average temperature, sunrise time and sunset time

### `GET /api/v1/weather/history`

#### Parameters

| Name          | Type     | Description    |
|---------------|----------|----------------|
| `latitude`    | `double` | **Required**.  |
| `longitude`   | `double` | **Required**.  |

#### Response

```json
{
  "averageTemperature": 15.970833333333333,
  "sunriseTime": "2023-06-02T04:33:00",
  "sunsetTime": "2023-06-02T20:43:00"
}
```
