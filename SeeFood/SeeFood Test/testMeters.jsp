void testMeters() {
        assertEquals(8046.72, server.convertMilesToMeters("5 mi"), 0.05);
    }

    void testMeters1() {
        assertEquals(16093.4, server.convertMilesToMeters("10 mi"), 0.05);
    }
