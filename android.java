TextView ipTextView = findViewById(R.id.ipTextView);

        new Thread(() -> {
            HttpURLConnection connection = null;
            try {
                URL url = new URL("https://api.ipify.org");
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(5000);
                connection.setReadTimeout(5000);

                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    reader.close();

                    String ip = response.toString();

                    runOnUiThread(() -> ipTextView.setText("Ваш IP: " + ip));
                }
            } catch (Exception e) {
                e.printStackTrace();
                runOnUiThread(() -> ipTextView.setText("Ошибка: " + e.getMessage()));
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }
        }).start();
