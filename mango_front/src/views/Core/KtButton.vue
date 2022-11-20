<template>
    <el-button :size="size" :type="type" :icon="icon"
        :loading="loading" :disabled="!hasPerms(perms)" @click="handleClick">
        {{label}}
    </el-button>
</template>

<script>
    import {hasPermission} from "@/permission/index.js";
    export default {
        name: "KtButton",
        props: {
            label: {
                type: String,
                default: "Button"
            },
            icon: {
                type: String,
                default: ""
            },
            size: {
                type: String,
                default: "mini"
            },
            type: {
                type: String,
                default: null
            },
            loading: {
                type: Boolean,
                default: false
            },
            disabled: {
                type: Boolean,
                default: false
            },
            perms: {
                type: String,
                default: null
            },
        },
        data() {
            return{}
        },
        methods: {
            handleClick() {
                this.$emit('click',{})
            },
            hasPerms(perms) {
                return hasPermission(perms) & !this.disabled
            }
        }
    }
</script>

<style scoped>

</style>