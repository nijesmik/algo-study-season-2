def find(groups):
    for index, group in enumerate(groups):
        if group == '':
            return index
    return -1

ipv6_input = input()

if ipv6_input.startswith('::'):
    ipv6_input = ipv6_input[1:]

elif ipv6_input.endswith('::'):
    ipv6_input = ipv6_input[:-1]

groups = ipv6_input.split(':')

while len(groups) < 8:
    insert_index = find(groups)
    groups.insert(insert_index, '0')

for i in range(len(groups)):
    groups[i] = groups[i].zfill(4)

expanded_ipv6 = ":".join(groups)

print(expanded_ipv6)

