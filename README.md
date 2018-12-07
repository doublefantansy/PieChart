# PieChart
圆形统计图控件


![sample](https://github.com/doublefantansy/PieChart/blob/master/sample.png)

主要用法:
           MyPieChart myPieChart = findViewById(R.id.pie_chart);
           final List<PieEntry> list = new ArrayList<>();
           list.add(new PieEntry("1", 2, getResources().getColor(R.color.colorPrimary)));
           list.add(new PieEntry("2", 2, getResources().getColor(R.color.myBlue)));
           list.add(new PieEntry("3", 2, getResources().getColor(R.color.colorAccent)));
           list.add(new PieEntry("4", 21, getResources().getColor(R.color.colorPrimaryDark)));
           list.add(new PieEntry("5", 5, getResources().getColor(R.color.black)));
           list.add(new PieEntry("6", 6, getResources().getColor(R.color.white)));
           list.add(new PieEntry("7", 7, getResources().getColor(R.color.gray)));
           list.add(new PieEntry("8", 8, getResources().getColor(R.color.purple)));
           myPieChart.setList(list);
           myPieChart.setClickPieEntryListener(new ClickPieEntryListener() {
            @Override
            public void click(PieEntry p) {
                Log.d("WOnb", p
                        .getNumber() + "");
            }
        });
