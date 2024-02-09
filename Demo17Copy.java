void main(){
    final String DATA = """
            5-Kasun Sampath-Galle,
            2-Amil Shantha-Panadura,
            10-Pradeep Kumara-Kandy,
            250-Ruwani-Matara,
            11-Supun-Panadura
            """;

   String dataCopy = DATA;

   dataCopy = dataCopy.replace("\n", "");
   int count = DATA.isBlank() ? 0 : (dataCopy.length() - dataCopy.replace(",", "").length()) + 1;

   body:
    {
        String ids = "", names = "", addresses = "";
        int commaIndex = 0, maximumNameLength = 0, maximumAddressLength = 0;
        if (count == 0) {
            System.out.println("\033[31mFile is Empty\033[0m");
            break body;
        }

        for (int rounds = 0; rounds < count; rounds++) {
            String studentDetails = dataCopy.substring(commaIndex, (rounds == count - 1 ? dataCopy.length() : dataCopy.indexOf(',', commaIndex)));

            int firstHyphenIndex = studentDetails.indexOf('-');
            int lastHyphenIndex = studentDetails.lastIndexOf('-');
            String id = String.format("S%03d", Integer.parseInt(studentDetails.substring(0, firstHyphenIndex)));
            String name = studentDetails.substring(++firstHyphenIndex, lastHyphenIndex);
            String address = studentDetails.substring(++lastHyphenIndex, studentDetails.length());

            ids += STR."\{id},";
            names += STR."\{name},";
            addresses += STR."\{address},";

            if (name.length() >= maximumNameLength) maximumNameLength = name.length();
            if (address.length() >= maximumAddressLength) maximumAddressLength = address.length();


            commaIndex = dataCopy.indexOf(',', commaIndex) + 1;
        }

        String blue = "\033[34m";
        String reset = "\033[0m";
        String labelId = "ID";
        String labelName = "Name";
        String labelAddress = "Address";

        System.out.println("+".concat("-".repeat(6)).concat("+").concat("-".repeat(maximumNameLength + 2)).concat("+").concat("-".repeat(maximumAddressLength + 2)).concat("+"));
        System.out.printf(STR."|\{blue}%-6s\{reset}|\{blue}%-\{maximumNameLength + 2}s\{reset}|\{blue}%-\{maximumAddressLength + 2}s\{reset}|\n", labelId, labelName, labelAddress);
        System.out.println("+".concat("-".repeat(6)).concat("+").concat("-".repeat(maximumNameLength + 2)).concat("+").concat("-".repeat(maximumAddressLength + 2)).concat("+"));

        int commaIndexId = 0, commaIndexName = 0, commaIndexAddress = 0;
        for (int rounds = 0; rounds < count; rounds++) {
            String studentId = ids.substring(commaIndexId, ids.indexOf(",", commaIndexId));
            String studentName = names.substring(commaIndexName, names.indexOf(",", commaIndexName));
            String studentAddress = addresses.substring(commaIndexAddress, addresses.indexOf(",", commaIndexAddress));

            commaIndexId = ids.indexOf(",", commaIndexId) + 1;
            commaIndexName = names.indexOf(",", commaIndexName) + 1;
            commaIndexAddress = addresses.indexOf(",", commaIndexAddress) + 1;

            System.out.printf(STR."|%-6s|%-\{maximumNameLength + 2}s|%-\{maximumAddressLength + 2}s|\n", studentId, studentName, studentAddress);

        }

        System.out.println("+".concat("-".repeat(6)).concat("+").concat("-".repeat(maximumNameLength + 2))
                .concat("+").concat("-".repeat(maximumAddressLength + 2)).concat("+"));
    }
}